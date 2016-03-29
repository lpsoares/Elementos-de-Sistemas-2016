load ShiftRegisterPIPO16.hdl,
output-file ShiftRegisterPIPO16.out,
compare-to ShiftRegisterPIPO16.cmp,
output-list time%S1.4.1 in%D1.2.1 direction%B2.6.2 out%D1.3.1;

set in 88,
set direction 000001,
tick,
output;

tock,
output;

set in 88,
set direction 000000,
tick,
output;

tock,
output;
