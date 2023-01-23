module dff_sync_res(D, clk, sync_reset, Q);
    input D;
    input clk;
    input sync_reset;
    output reg Q;

    always @(posedge clk) 
    begin
        // reset condition:
        if(sync_reset == 1'b1)
            Q <= 1'b0;
        else 
            Q <= D;
    end
endmodule 