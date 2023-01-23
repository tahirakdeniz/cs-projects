`timescale 1ns/10ps
module two_s_complement_tb;

   reg [3:0] In;
   wire [3:0] Out;
   reg [3:0] num = 4'b0000;

   two_s_complement test(In, Out);

   initial begin
      $dumpfile("two_s_complement.vcd");
      $dumpvars;

      for(integer i = 0; i < 16; i++) begin
         In = num;
         #10
         num += 1;
      end
   end

endmodule 
