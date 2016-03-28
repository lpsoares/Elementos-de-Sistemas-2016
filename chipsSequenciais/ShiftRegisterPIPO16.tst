load ShiftRegisterPIPO16.hdl,
output-file ShiftRegisterPIPO16.out,
compare-to ShiftRegisterPIPO16.cmp,
output-list time%S1.4.1 in%D1.6.1 load%B2.1.2 out%D1.6.1;

set in 1010010000111001,
set load 1,
tick,
output;

tock,
output;

set in 1010010000111001,
set load 0,
tick,
output;

tock,
output;
