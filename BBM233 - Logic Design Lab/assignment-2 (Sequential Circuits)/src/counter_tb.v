`timescale 1ns/1ps

module counter_tb;
    // inputs:
    reg reset, clk, mode; 
    wire [2:0] count;
    integer i;
	
	// Comment the next line out when testing your JK flip flop implementation.
    counter_d uut(reset, clk, mode, count);
    
    // Uncomment the next line to test your JK flip flop implementation.
    // counter_jk c1(reset, clk, mode, count);

    initial begin
        $dumpfile("result.vcd");
        $dumpvars;

        // wait to see results after change variables

        i = 0;
        mode = 0;
        reset = 1; 
        #15;

        reset = 0;
        #200;
        
        mode = 1;
        #200
        
        reset = 1;
        #50

        $finish;
    end

    // clock to test
    initial begin
        clk = 0;
        forever begin
            #10;
            clk = ~clk;
        end
    end

endmodule