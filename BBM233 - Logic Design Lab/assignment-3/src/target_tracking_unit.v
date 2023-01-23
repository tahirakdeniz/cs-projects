`timescale 1us/1ns

module target_tracking_unit(
    input rst,
    input track_target_command,
    input clk,
    input echo,
    output reg trigger_radar_transmitter,
    output reg [13:0] distance_to_target,
    output reg target_locked,
    output [1:0] TTU_state
);
    // define constant parameters
    parameter integer C = 300000000;

    // define parameters for states
    parameter IDLE_STATE = 2'b00;
    parameter TRANSMIT_STATE = 2'b01;
    parameter LISTEN_FOR_ECHO_STATE = 2'b10;
    parameter TRACK_STATE = 2'b11;

    // define registers for current and next state
    reg [1:0] current_state = IDLE_STATE;
    reg [1:0] next_state = IDLE_STATE;

    // assign output
    assign TTU_state = current_state;

    // define registers for time
    reg [0:31] starting_time = 0;
    reg [0:31] current_time = 0;
    
    reg [0:31] transmit_time = 0; // to keep when to run transmit state first.
    reg [0:31] listen_time = 0; // to keep when to run listen echo state first.
    reg [0:31] track_time = 0;
    reg [0:31] trigger_time = 0; // time to when radar trigger is finished.


    reg [0:31] waiting_time = 0;

    reg [0:63] ans;

    reg echo_status = 0; // bool to keep status of the echo signal has came.

    // reset
    always @(posedge rst) begin
        current_state <= IDLE_STATE;
        next_state = IDLE_STATE;
        distance_to_target = 0;
        trigger_radar_transmitter = 0;
        target_locked = 0;
    end

    // new state logic
    always @(posedge clk) begin
        case (current_state)
            IDLE_STATE: begin
                if(track_target_command == 1) begin
                    current_state = TRANSMIT_STATE;
                end
            end 

            TRANSMIT_STATE: begin
                if($stime - transmit_time == 50) begin
                    current_state = LISTEN_FOR_ECHO_STATE;
                end
                    
            end

            LISTEN_FOR_ECHO_STATE: begin
                if($stime - listen_time == 100) begin
                    current_state = IDLE_STATE;
                end
                else
                    if(echo_status == 1) begin
                        current_state = TRACK_STATE;
                        echo_status = 0;
                    end
                        
            end

            TRACK_STATE: begin
                if(track_target_command == 1)
                    current_state = TRANSMIT_STATE;
                else
                    if($stime - track_time == 300)
                        current_state = IDLE_STATE;
            end
        endcase
    end

    // timer starting logic
    always @(current_state) begin
        case (current_state)
            IDLE_STATE: begin 
                target_locked = 0;
                distance_to_target = 0;
            end 

            TRANSMIT_STATE: begin
                transmit_time = $stime;
            end

            LISTEN_FOR_ECHO_STATE: begin
                listen_time = $stime;
            end

            TRACK_STATE: begin
                track_time = $stime;
            end
        endcase
    end

    ////// OUTPUT LOGIC //////

    // make trigger_radar_transmitter is high for 50
    always @(posedge track_target_command) begin
        trigger_radar_transmitter = 1;
        #50;
        trigger_radar_transmitter = 0;
        trigger_time = $stime;
    end

    // calculate distance when @ posedge echo
    always @(posedge echo) begin
        if(current_state == LISTEN_FOR_ECHO_STATE) begin
            target_locked = 1;
            echo_status = 1;
            distance_to_target = (($stime - trigger_time)*300/2); // calculate distance
        end

    end
endmodule