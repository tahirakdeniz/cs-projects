`timescale 1 ns/10 ps
module full_adder_tb;
    reg A;
    reg B;
    reg Cin;
    wire S;
    wire Cout;

    reg [2:0] num = 3'b000;
    full_adder test(A, B, Cin, S, Cout);

    initial begin
        $dumpfile("full_adder.vcd");
        $dumpvars;

        for(integer i = 0; i < 8; i++) begin
            {Cin, A, B} = num;
            #10
            num += 1;
        end
    end

endmodule