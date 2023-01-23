`timescale 1us/1ns

module weapons_control_unit_tb;
    reg target_locked;
    reg clk;
    reg rst;
    reg fire_command;
    wire launch_missile;

    wire [1:0] WCU_state;
    wire [3:0] remaining_missiles;

    weapons_control_unit uut(target_locked, clk, rst, fire_command, launch_missile, remaining_missiles, WCU_state);

    initial begin
        $dumpfile("weapons_control_unit_result.vcd");
        $dumpvars;
        fire_command = 0;
        target_locked = 0;
        rst = 1;
        #17
        
        rst = 0;
        #3;

        target_locked = 1;
        #20;

        target_locked = 0;
        fire_command = 0;
        target_locked = 1;
        #20;

        target_locked = 1;
        #20;

        fire_command = 1;
        #10;

        target_locked = 0;
        #20;

        target_locked = 1;
        #150;
        
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