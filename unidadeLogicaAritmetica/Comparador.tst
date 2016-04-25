load Comparador.hdl,
output-file Comparador.out,
compare-to Comparador.cmp,
output-list in%B1.16.1 ng%B1.1.1 zr%B1.1.1;

set in %B0000000000000000,
eval,
output;

set in %B0000000000000001,
eval,
output;

set in %B1000000000000000,
eval,
output;
