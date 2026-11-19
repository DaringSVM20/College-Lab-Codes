module half_adder(a,b,s,c);
    input a,b;
    output s,c;
    assign s=a^b;
    assign c=a&b;
endmodule

module multi2(a,b,p);
    input [1:0] a,b;
    output [3:0] p;
    
    wire p1,p2,p3;
    wire s1,s2,c1,c2;

    assign p[0]=a[0]&b[0];
    assign p1=a[1]&b[0];
    assign p2=a[0]&b[1];
    assign p3=a[1]&b[1];

    half_adder h1(p1,p2,s1,c1);
    half_adder h2(c1,p3,s2,c2);

    assign p[1]=s1;
    assign p[2]=s2;
    assign p[3]=c2;

endmodule
