module mux4_1(w,s,f);
    input [3:0] w;
    input [1:0] s;
    output f;
    wire f1, f2;

    mux2_1 m1(w[1:0], s[0], f1);
    mux2_1 m2(w[3:2], s[0], f2);
    mux2_1 m3({f1, f2}, s[1], f);
endmodule

module mux2_1(w,s,f);
    input [1:0] w;
    input s;
    output f;
    assign f = (s ? w[1] : w[0]);
endmodule
