`timescale 1ns/10ps
module four_bit_2x1_mux_tb;
	reg [3:0] In_1;
	reg [3:0] In_0;
	reg Select;
	wire [3:0] Out;
	reg [8:0] num = 9'b000000000;

	four_bit_2x1_mux test(In_1, In_0, Select, Out);

	initial begin
		$dumpfile("four_bit_2x1_mux_tb.vcd");
		$dumpvars;

		for(integer i = 0; i < 512; i++) begin
			{Select, In_1, In_0} = num;
			#10
			num += 1;
		end
	end
	
endmodule
