module q2(w1, w2, w3, f);
    input w1,w2,w3;
    output f;
    wire [3:0]in ;
    assign in[0]=0;
    assign in[1]=w1;
    assign in[2]=w1;
    assign in[3]=0;
endmodule