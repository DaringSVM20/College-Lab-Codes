module q4(w,f);
    input [3:1]w;
    output f;
    wire temp1, temp2;
    assign temp1 = w[2] & w[3];
    assign temp2 = w[2] | w[3];
    assign f = (w[1] ? temp2 : temp1);
endmodule