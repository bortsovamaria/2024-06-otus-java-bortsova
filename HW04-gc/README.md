# Домашнее задание

Определение нужного размера хипа

### Цель:

на примере простого приложения понять какое влияние оказывают сборщики мусора.

познакомиться со сборщиком мусора в Java

### До оптимизации:

| heap (MB) | spend msec | sec    |
|-----------|------------|--------|
| 256       | 32752      | 32     |
| 2048      | 27540      | 27     |
| 4096      | 22656      | 22     |
| 8192      | 12674      | 12     |
| **8100**  | **12462**  | **12** |
| 8000      | 12974      | 12     | 

__________________________________

**8100 mb** - оптимальный размер heap

### После оптимизации:

1) Integer -> int
2) List<Data> -> переиспользуемый статический массив Data[] с размером 100_000
3) Переиспользовать объект Data, используя setter

| heap (MB) | spend msec | sec |
|-----------|------------|-----|
| 256       | 1471       | 1   |
| 128       | 1472       | 1   |
| 64        | 1477       | 1   |
| 32        | 1368       | 1   |
| 16        | 1473       | 1   |
__________________________________

**32 mb** - оптимальный размер heap

Вывод:

    использование примитивов в данном случае сокращает оптимальный размер heap в ~250 раз и время выполнения в ~12 раз