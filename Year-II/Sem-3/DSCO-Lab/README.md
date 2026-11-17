# Digital Systems and Computer Organisation Lab

Semester 3 - MIT Manipal, Data Science & Engineering.

## What this covers

Digital Systems and Computer Organisation lab, written in Verilog HDL. Each design module is paired with its own testbench (named tb_* or *_tb), simulated with Icarus Verilog. Waveform dumps (.vcd) produced by the testbenches are committed alongside the sources so the simulated behaviour can be inspected without re-running anything.

Topics: Verilog HDL, combinational logic, multiplexers, decoders, encoders, flip-flops, counters.

## Contents

### Week 0 and Week 1 - first Verilog modules

The opening Verilog exercises. Week 0 is a single module with its testbench, written to get the Icarus Verilog toolchain working end to end, and includes a screenshot of the first successful simulation. Week 1 adds two more design/testbench pairs. Each testbench dumps a .vcd waveform, which is committed next to the source.

## Folders

- [`Week-00/`](Week-00) - 2 Verilog modules, 1 Screenshot, 1 Waveform dump
- [`Week-01/`](Week-01) - 4 Verilog modules, 2 Waveform dumps

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
