use strict;
use warnings;

my %counter;
my $file = shift or die "Please enter a file name. For example, perl $0 input.txt\n";
open my $fh, '<', $file or die "Could not open '$file' $!";

while (my $input = <$fh>) {
   chomp $input;

   foreach my $word (split /\W+/, $input) {
     $counter{$word}++;
   }
}

my @sortedResult = (reverse sort { $counter{$a} <=> $counter{$b} } keys %counter);

foreach my $word (@sortedResult) {
    printf "%s %s\n", $counter{$word}, $word;
}