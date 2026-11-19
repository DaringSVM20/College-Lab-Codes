// 2to4 decoder active high input and output using if-else statements. Using these 2to4 decoders, create 3to8 decoder and write Verilog code for the same.

module dec2to4(w, en, y);
    input [1:0] w;
    input en;
    output reg [3:0] y;

    always @(*) begin
      if(en) begin
        case(w)
            2'b00: y = 4'b0001;
            2'b01: y = 4'b0010;
            2'b10: y = 4'b0100;
            2'b11: y = 4'b1000;
        endcase
        end
      else y = 4'b0000;
    end
endmodule

module dec3to8(w, en, y);
    input [2:0] w;
    input en;
    output [7:0] y;
    
    wire [1:0]e;
    assign e[0] = en & ~w[2];
    assign e[1] = en & w[2];

    dec2to4 d0(w[1:0], e[0], y[3:0]);
    dec2to4 d1(w[1:0], e[1], y[7:4]);
endmodule

