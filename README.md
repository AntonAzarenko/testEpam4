# testEpam4

Построить веб-систему, поддерживающую заданную функциональность:
1.	Разработать схему базы данных в воответствии с предметной областью вашего варианта. Создать sql-скрипты создания БД, пользователя БД, создание таблиц, заполнение таблиц, удаление данных, удаление таблиц, удаление БД, обновление данных, запросы на выборку данных.
2.	Информацию о предметной области хранить в БД, для доступа использовать API JDBC с использованием пула соединений, стандартного или разработанного самостоятельно. В качестве СУБД рекомендуется MySQL или Derby.
3.	На основе сущностей предметной области создать классы их описывающие.
4.	Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам.
5.	Оформление кода должно соответствовать Java Code Convention.
6.	Приложение должно поддерживать работу с кириллицей (быть многоязычной), в том числе и при хранении информации в БД.
7.	Архитектура приложения должна соответствовать шаблону Model-View-Controller.
8.	При реализации алгоритмов бизнес-логики использовать шаблоны GoF: Factory Method, Command, Builder, Strategy, State, Observer etc.
9.	Выполнить журналирование событий, то есть информацию о возникающих исключениях и событиях в системе обрабатывать с помощью Log4j.
10.	Код должен содержать комментарии.

Модель:
Система Периодические издания. Администратор осуществляет ведение каталога периодических Изданий. 
Читатель может оформить Подписку, предварительно выбрав периодические Издания из списка. 
Система подсчитывает сумму для оплаты и регистрирует Платеж.

Система построена на сервлетах!Сейчас переводиться на Spring/Hibernate в ветке spring
