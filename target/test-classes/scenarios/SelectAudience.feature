Feature: Тестирование сайта ИНВИТРО

  Background: Находимся на главной странице сайта
    Given Открыт сайт "https://www.invitro.ru/"

  Scenario: Выбрать аудиторию
    And Нажать кнопку Выбора аудитории и выбрать "Корпоративным клиентам"