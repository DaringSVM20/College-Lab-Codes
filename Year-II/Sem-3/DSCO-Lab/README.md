# Digital Systems and Computer Organisation Lab

Semester 3 - MIT Manipal, Data Science & Engineering.

## What this covers

Digital Systems and Computer Organisation lab, written in Verilog HDL. Each design module is paired with its own testbench (named tb_* or *_tb), simulated with Icarus Verilog. Waveform dumps (.vcd) produced by the testbenches are committed alongside the sources so the simulated behaviour can be inspected without re-running anything.

Topics: Verilog HDL, combinational logic, multiplexers, decoders, encoders, flip-flops, counters.

## Contents

### Week 0 and Week 1 - first Verilog modules

The opening Verilog exercises. Week 0 is a single module with its testbench, written to get the Icarus Verilog toolchain working end to end, and includes a screenshot of the first successful simulation. Week 1 adds two more design/testbench pairs. Each testbench dumps a .vcd waveform, which is committed next to the source.

### Week 2 and Lab 5 - combinational logic and multiplexers

Week 2 is four combinational designs (lab2q1a/b and lab2q2a/b), each with its own testbench and waveform dump. Lab 5 is the multiplexer set: a 2:1 multiplexer, a 4:1, and an 8:1 built by composing smaller 2:1 instances - a first look at structural, hierarchical Verilog rather than single flat modules.

### Decoders, encoders, flip-flops and counters

The remaining DSCO work. The lab 6 practice builds a 3:8 decoder out of two 2:4 decoders and a 4:2 priority encoder. The lab 9 practice moves into sequential logic: JK, T and D flip-flops, sequence detectors built on them, and a synchronous counter made from T flip-flops. The test folder holds extra combinational designs - a half adder, a 2-bit multiplier built from half adders, and a 4:1 multiplexer composed of 2:1 instances.

## Folders

- [`Lab-05-multiplexers/`](Lab-05-multiplexers) - 8 Verilog modules, and a few others
- [`Practice/`](Practice) - 22 Verilog modules, 8 Waveform dumps
- [`Week-00/`](Week-00) - 2 Verilog modules, 1 Screenshot, 1 Waveform dump
- [`Week-01/`](Week-01) - 4 Verilog modules, 2 Waveform dumps
- [`Week-02/`](Week-02) - 8 Verilog modules, 1 Text

## Requirements

Icarus Verilog (iverilog and vvp) to compile and simulate. GTKWave to open the .vcd waveform dumps. Both are available on Linux, macOS and Windows.

## How to run

```sh
iverilog -o sim q1.v tb_q1.v
vvp sim
gtkwave dump.vcd     # opens the waveform the testbench dumped
```

`Lab-Manual.pdf` in this folder is the lab manual these exercises were set from.

---

Part of my college lab work, shared openly - juniors and classmates are welcome
to use it.
