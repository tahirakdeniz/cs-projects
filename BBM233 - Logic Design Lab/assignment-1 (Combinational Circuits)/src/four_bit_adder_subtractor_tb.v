`timescale 1ns/1ps
module four_bit_adder_subtractor_tb;
    reg [3:0] A;
    reg [3:0] B;
    reg subtract;

    wire [3:0] Result;
    wire Cout;

    reg [8:0] num = 9'b000000000;

    four_bit_adder_subtractor test(A, B, subtract, Result, Cout);

    initial begin
        $dumpfile("four_bit_adder_subtractor.vcd");
        $dumpvars;

        for(integer i = 0; i < 512; i++) begin
            {subtract, A, B} = num;
            #10
            num += 1;
        end
    end
endmodule
