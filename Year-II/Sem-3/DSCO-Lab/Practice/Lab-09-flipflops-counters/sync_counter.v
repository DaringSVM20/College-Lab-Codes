// 3-bit sync counter using T flip-flops
module tff (q, t, clr, clk);
    input t,clr,clk;
    output reg q;
    always @(posedge clk or negedge clr)
    begin
        if (!clr)
            q<=0;
        else if (t)
            q<=~q;
        else
            q<=q;
    end
endmodule

module sync_counter (clr, clk, q);
    input clr, clk;
    output [2:0]q;
    wire a,b,c;
    
    assign a=1'b1;
    assign b=q[0];
    assign c=q[0]&q[1];

    tff ff0(q[0], a, clr, clk);
    tff ff1(q[1], b, clr, clk);
    tff ff2(q[2], c, clr, clk);
endmodule