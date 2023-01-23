`timescale 1us/1ns

module weapons_control_unit(
    input target_locked,
    input clk,
    input rst,
    input fire_command,
    output reg launch_missile,
    output [3:0] remaining_missiles,
    output [1:0] WCU_state
);
    // state paramameters:
    parameter IDLE_STATE = 2'b00;
    parameter TARGET_LOCKED_STATE = 2'b01;
    parameter FIRE_STATE = 2'b10;
    parameter OUT_OF_AMMO_STATE = 2'b11;
    // ---

    reg [1:0] current_state = IDLE_STATE;

    reg [2:0] rem_mis = 4;

    assign WCU_state = current_state;
    assign remaining_missiles = rem_mis;

    // state logic
    always @(posedge clk) begin
        if(rst) begin
            // reset
            current_state <= IDLE_STATE;
            launch_missile = 0;
        end  
        else begin
            case (current_state)
                IDLE_STATE: begin
                    if(target_locked == 1)
                        current_state <= target_locked;
                end
                TARGET_LOCKED_STATE: begin
                    if(target_locked == 0)
                        current_state <= IDLE_STATE;
                    else if(fire_command == 1) begin
                        current_state <= FIRE_STATE;
                    end
                end

                FIRE_STATE: begin
                    if(target_locked && rem_mis != 0) 
                        current_state <= TARGET_LOCKED_STATE;
                    else begin
                        if(!target_locked && rem_mis != 0) 
                            current_state <= IDLE_STATE;
                        else 
                            if(!rem_mis) begin
                                current_state <= OUT_OF_AMMO_STATE;
                            end
                    end
                end
                
                OUT_OF_AMMO_STATE: begin end
            endcase
        end
    end

    // output logic:
    always @(current_state) begin
        case (current_state)
            FIRE_STATE: begin
                launch_missile = 1;
                if(rem_mis != 0)
                    rem_mis = rem_mis - 1;
                #10;
                launch_missile = 0;
            end
        endcase
    end
endmodule