module jk_ff(j,k,clk,rst,q);
    input j,k,clk,rst;
    output reg q;
    always @(posedge clk)

    if (rst)
        q <= 0;
    else begin
      case ({j,k})
        2'b00: q <= q;
        2'b01: q <= 0;
        2'b10: q <= 1;
        2'b11: q <= ~q;
      endcase
    end
endmodule

module jk_seq(E,x,clk,rst,state);
    input E,x,clk,rst;
    output [1:0] state;

    wire ja,ka,jb,kb;
    reg [1:0] next;
    wire [1:0] curr;

    jk_ff A(ja, ka, clk, rst, curr[1]);
    jk_ff B(jb, kb, clk, rst, curr[0]);

    assign state = curr;

    always @(*) begin
        case (curr)
            2'b00: begin
                if (E == 0) next = 2'b00;
                else if (E == 1 && x == 0) next = 2'b01;
                else next = 2'b10;
            end
            2'b01: begin
                if (E == 0) next = 2'b00;
                else if (E == 1 && x == 0) next = 2'b10;
                else next = 2'b11;
            end
            2'b10: begin
                if (E == 0) next = 2'b00;
                else if (E == 1 && x == 0) next = 2'b11;
                else next = 2'b01;
            end
            2'b11: begin
                if (E == 0) next = 2'b00;
                else if (E == 1 && x == 0) next = 2'b01;
                else next = 2'b10;
            end
            default: next = 2'b00;
        endcase
    end 

    assign ja = (curr[1] ~^ next[1]) & (curr[0] ^ next[0]);
    assign ka = (curr[1] ^ next[1]) & (curr[0] ^ next[0]);
    assign jb = (curr[1] ^ next[1]) & (curr[0] ~^ next[0]);
    assign kb = (curr[1] ^ next[1]) & (curr[0] ^ next[0]);
    
endmodule