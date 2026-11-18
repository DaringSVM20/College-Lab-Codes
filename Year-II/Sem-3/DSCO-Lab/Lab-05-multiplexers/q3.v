//16to1 mux using 4to1 mux

module mux_4to1(w,s,f);
    input [3:0] w;
    input [1:0] s;
    output reg f;
    always @(*)
    begin
    f = (s[0])? (s[1]? w[3]:w[2]) : (s[1]? w[1]:w[0]);
    end
endmodule

module q3(W,S,f);
   input [15:0]W;
   input [3:0]S;
   output f;
   wire [3:0]fi;
   mux_4to1 m1(W[3:0],S[1:0],fi[0]);
   mux_4to1 m2(W[7:4],S[1:0],fi[1]);
   mux_4to1 m3(W[11:8],S[1:0],fi[2]);
   mux_4to1 m4(W[15:12],S[1:0],fi[3]);
   mux_4to1 m5(fi,S[3:2],f);
   
endmodule
