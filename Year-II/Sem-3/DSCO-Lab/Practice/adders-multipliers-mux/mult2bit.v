module half_adder(w, f);
  input [1:0] w;     // w[0] = A, w[1] = B
  output [1:0] f;    // f[0] = SUM, f[1] = CARRY

  assign f[0] = w[0] ^ w[1];  // SUM = A XOR B
  assign f[1] = w[0] & w[1];  // CARRY = A AND B
endmodule

module mult2bit(a, b, p);
  input [1:0] a, b;
  output [3:0] p;

  wire p1, p2, p3;
  wire [1:0] s1, s2;   // outputs of half adders

  assign p[0] = a[0] & b[0];   // least significant bit
  assign p1   = a[1] & b[0];
  assign p2   = a[0] & b[1];
  assign p3   = a[1] & b[1];

  // First half adder: p1 + p2
  half_adder ha1({p1, p2}, s1);   // s1[0]=sum, s1[1]=carry

  // Second half adder: s1[1] + p3
  half_adder ha2({s1[1], p3}, s2);

  assign p[1] = s1[0];  // result of first HA
  assign p[2] = s2[0];  // sum of carry + p3
  assign p[3] = s2[1];  // final carry
endmodule
