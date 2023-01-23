module counter_d(input reset, input clk, input mode, output [2:0] count);
    
    // It is needed 3 flip flop to represent given states
    // A = count[2]
    // B = count[1]
    // C = count[0]
    
    // flip flop for A
    dff_sync_res d_a (
        .D(
            // derived input and output functions using Karnaugh Maps for A
            (count[1] & ~count[0] & mode) 
            | (~count[2] & count[1] & count[0] & ~mode) 
            | (count[2] & ~count[1] & ~mode) 
            | (count[2] & count[0] & mode) 
            | (count[2] & ~count[0] & ~mode)
        ),
        .clk(clk),
        .sync_reset(reset),
        .Q(count[2])
    );

    // flip flop for B
    dff_sync_res d_b(
        .D(
            // derived input and output functions using Karnaugh Maps for B
            (count[1] & ~count[0]) 
            | (~count[1] & count[0] & ~mode) 
            | (~count[2] & count[0] & mode)),
        .clk(clk),
        .sync_reset(reset),
        .Q(count[1])
    );

    // flip flop for C
    dff_sync_res d_c(
        .D(
            // derived input and output functions using Karnaugh Maps for C
            (~count[2] & ~count[1] & mode) 
            | (~count[0] & ~mode) 
            | (count[2] & count[1] & mode)),
        .clk(clk),
        .sync_reset(reset),
        .Q(count[0])
    );

endmodule
