// q2b.v
module lab2q2b(a,b,c,d,f);
  input a,b,c,d;
  output f;
  assign f = (a & c & ~b) | (a & d & ~c) | (b & ~c & ~d) | (c & ~a & ~d);
endmodule
