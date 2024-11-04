<%@ page contentType="text/html;charset=UTF-8"%>
<div>
    <h3>${wordBundle.getWord("charm")} <3</h3>
    <form action="/lang" method="post">
        <button name="lang" value="kz">Қазақша</button>
        <button name="lang" value="ru">Русский</button>
        <button name="lang" value="en">English</button>
    </form>
    <hr>
</div>