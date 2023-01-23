module full_adder(
    input A,
    input B,
    input Cin,
    output S,
    output Cout
);

    assign {Cout, S} = A + B + Cin;

endmodule