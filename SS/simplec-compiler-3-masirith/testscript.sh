for i in tests/*.simplec; do
  cat $i | ./simplec > ${i%.simplec}.s 2>/dev/null;
  diff -q <(cat ${i%.simplec}.s) ${i%.simplec}.s;
  echo "Exit code: $?"
done