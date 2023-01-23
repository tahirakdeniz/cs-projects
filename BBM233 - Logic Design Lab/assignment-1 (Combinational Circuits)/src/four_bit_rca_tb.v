`timescale 1 ns/10 ps
module four_bit_rca_tb;
  reg [3:0] A;
  reg [3:0] B;
  reg Cin;

  wire Cout;
  wire [3:0] S;


  reg [8:0] num = 9'b000000000;


  four_bit_rca test(A, B, Cin, S, Cout);

  initial begin
        $dumpfile("four_bit_rca.vcd");
        $dumpvars;

        for(integer i = 0; i < 512; i++) begin
            {Cin, A, B} = num;
            #10
            num += 1;
        end
    end
// Your code goes here.  DO NOT change anything that is already given! Otherwise, you will not be able to pass the tests!

endmodule