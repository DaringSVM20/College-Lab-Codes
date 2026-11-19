`timescale 1ns/1ns
`include "dec3to8.v"

module dec3to8tb;
    reg [2:0] w;
    reg en;
    wire [7:0] y;

    dec3to8 dut (.w(w), .en(en), .y(y));

    initial begin
        $dumpfile("dec3to8.vcd");
        $dumpvars(0, dec3to8tb);

        $monitor("en=%b in=%b -> out=%b", en, w, y);
        en=1;
        w=3'b000; #10;
        w=3'b011; #10;
        w=3'b100; #10;
        w=3'b111; #10;
        $finish;
    end
endmodule