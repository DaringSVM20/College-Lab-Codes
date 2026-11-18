// q1a.v
module lab2q1a(a,b,c,d,f);
  input a,b,c,d;
  output f;
  assign f = (c & d) | (b & ~a) | (c & ~b) | (b & ~c & ~d);
endmodule
