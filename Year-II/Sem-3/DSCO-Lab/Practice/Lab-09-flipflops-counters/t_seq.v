module t_ff(T, clk, rst, q);
    input T, clk, rst;
    output reg q;
    always @(posedge clk) begin
    if (rst)
        q <= 0;
    else if (T)
        q <= ~q;
    end
endmodule

module t_seq(x, clk, rst, state);
    input x, clk, rst;
    output [1:0]state;
    wire ta, tb;
    wire A,B;

    assign ta = (B & (~A | ~x));
    assign tb = ((~x & (~A | ~B)) | (~A & B & x));

    t_ff ff_A(ta, clk, rst, A);
    t_ff ff_B(tb, clk, rst, B);

    assign state = {A, B};
endmodule