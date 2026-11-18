//16to1 mux using 8to1 and 2to1

module mux_2to1(w,s,f);
    input s;
    input [1:0]w;
    output reg f;
    always @(*) //star implies sensitiviy for all inputs
    begin
    if (s==0)
        f = w[0];
    else
        f=w[1];
    end
endmodule

module mux_8to1(w,s,f);
    input [7:0]w;
    input [2:0]s;
    output reg f;
    always@(*)
    begin
    case(s)
        3'b000: f= w[0];
        3'b001: f= w[1];
        3'b010: f= w[2];
        3'b011: f= w[3];
        3'b100: f= w[4];
        3'b101: f= w[5];
        3'b110: f= w[6];
        3'b111: f= w[7];
    endcase
    end
endmodule

module q4(w,s,f);
    input [15:0]w;
    input [3:0]s;
    output f;
    wire [1:0]fi;
    mux_8to1 m1(w[7:0],s[2:0],fi[0]);
    mux_8to1 m2(w[15:8],s[2:0],fi[1]);
    mux_2to1 m3(fi,s[3],f);
endmodule
