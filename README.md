# lab3_map
lab3 Hazelcast distributed map

1. Distributed Map / Backup / отказоустойчивость

Для кластера из трех нод запускаем Member.java (дважды), потом нужно раскомментировать участок кода для записи значений.
Далее останавливаем две ноды (в Eclipse - кнопка "stop"); это нужно делать максимально быстро.
Запускаем Client.java, в результате мы увидим размер keyset.

2. PessimisticLocking

Нужно запустить PessimisticLocking.java (трижды для кластера из трех нод); это нужно делать максимально быстро.
Через некоторое время на одной из нод мы увидим:
Finished! Result = 3000
Если значение меньше, нужно перезапустить.

3. OptimisticLocking

Нужно запустить: OptimisticLockingMember2.java и 2 раза OptimisticLockingMember.java

4. Условие гонок

Нужно запустить RacyUpdaterMember.java
В результате мы должны получить значение, которое отличается от 1000.

5. Replicated Map

В результате получаем одинаковые значение на всех нодах.
