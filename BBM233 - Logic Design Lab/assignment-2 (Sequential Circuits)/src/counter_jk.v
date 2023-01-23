module counter_jk(input reset, input clk, input mode, output [2:0] count);
    
    // It is needed 3 flip flop to represent given states
    // A = count[2]
    // B = count[1]
    // C = count[0]

    jk_sync_res j_A(
        // derived input and output functions (J AND K) using Karnaugh Maps for A
        .J((count[1] & ~count[0] & mode) | (count[1] & count[0] & ~mode)),
        .K((~count[1] & ~count[0] & mode) | (count[1] & count[0] & ~mode)),
        .clk(clk),
        .sync_reset(reset),
        .Q(count[2])
    );

    jk_sync_res j_B(
        // derived input and output functions (J AND K) using Karnaugh Maps for B
        .J((count[0] & ~mode) | (~count[2] & count[0])),
        .K((count[0] & ~mode) | (count[2] & count[0])),
        .clk(clk),
        .sync_reset(reset),
        .Q(count[1])
    );

    jk_sync_res j_C(
        // derived input and output functions (J AND K) using Karnaugh Maps for C
        .J((~count[2] & ~count[1]) | (~mode) | (count[2] & count[1])),
        .K((~mode) | (~count[2] & count[1]) | (count[2] & ~count[1])),
        .clk(clk),
        .sync_reset(reset),
        .Q(count[0])
    );

endmodule