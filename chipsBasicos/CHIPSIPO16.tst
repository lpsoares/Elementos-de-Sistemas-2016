// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/03/a/Register.tst

load CHIPSIPO16.hdl,
output-file CHIPSIPO16.out,
compare-to CHIPSIPO16.cmp,
output-list time%S1.4.1 in%D2.1.2 load%B2.1.2 out%B1.16.1;

set in 1,
set load 0,
tick,
tock,

set in 1,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 1,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 1,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 0,
set load 0,
tick,
tock,

set in 1,
set load 0,
tick,
tock,

set in 1,
set load 0,
tick,
tock,

set in 1,
set load 1,
tick,
tock,
output;