//4to1 multiplexer using 2to1 multiplexer

module mux_2to1(w0,w1,s,f);
    input w0,w1,s;
    output reg f;
    always @(*) //star implies sensitiviy for all inputs
    begin
    if (s==0)
        f = w0;
    else
        f=w1;
    end
endmodule

module q2(w0,w1,w2,w3,s0,s1,f);
    input w0,w1,w2,w3,s0,s1;
    output f;
    wire f1,f2;
    mux_2to1 m1(w0,w1,s0,f1);
    mux_2to1 m2(w2,w3,s0,f2);
    mux_2to1 m3(f1,f2,s1,f);
endmodule
