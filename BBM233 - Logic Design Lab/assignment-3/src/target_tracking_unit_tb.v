`timescale 1us/1ns

module target_tracking_unit_tb;
    reg rst;
    reg track_target_command;
    reg clk;
    reg echo;
    wire trigger_radar_transmitter;
    wire [13:0] distance_to_target;
    wire target_locked;
    wire [1:0] TTU_state;

    target_tracking_unit uut(rst, track_target_command, clk, echo, trigger_radar_transmitter, distance_to_target, target_locked, TTU_state);

    initial begin
        $dumpfile("target_tracking_unit_result.vcd");
        $dumpvars;

        track_target_command = 0;
        echo = 0;
        rst = 1;
        #10;
        rst = 0;
        #20;
        track_target_command = 1;
        #10;
        track_target_command = 0;
        #75
        echo = 1;
        #1;
        echo = 0;
        #500;
        $finish;
    end

    initial begin
        clk = 0;
        forever begin
            #5;
            clk = ~clk;
        end
    end

endmodule
