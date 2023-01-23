// `include "two_s_complement.v"
// `include "four_bit_2x1_mux.v"
// `include "four_bit_rca.v"
module four_bit_adder_subtractor(A, B, subtract, Result, Cout);
    input [3:0] A;
    input [3:0] B;
    input subtract;
    output [3:0] Result;
    output Cout;

    wire [3:0] bcom;
    wire [3:0] bMux;
    
    reg cin = 0;

    two_s_complement tc(B, bcom);
    four_bit_2x1_mux mux(bcom, B, subtract, bMux);
    four_bit_rca rca(A, bMux, cin, Result, Cout);

endmodule
