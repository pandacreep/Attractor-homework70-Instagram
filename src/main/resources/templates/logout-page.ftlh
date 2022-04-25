<#import "partials/main.ftlh" as main />

<@main.renderWith scripts=[]>

    <h1 class="my-3">Please confirm logout</h1>
    <form class="flex box-128" action="/logout" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <button class="btn btn-primary"> Logout </button>
    </form>

</@main.renderWith>