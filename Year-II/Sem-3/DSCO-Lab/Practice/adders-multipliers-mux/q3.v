module q3(w,f);
    input [3:1]w;
    output f;
    wire temp;
    assign temp=w[2]^w[3];
    assign f=(w[1] ? (~temp) : temp);
endmodule