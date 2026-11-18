// q1b.v
module lab2q1b(a,b,c,d,f);
  input a,b,c,d;
  output f;
  assign f = (~b & (c | d)) | (b & ~c & ~d);
endmodule
