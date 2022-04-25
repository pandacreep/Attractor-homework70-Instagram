<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Please login</h1>
    <form action="/login" method="post">
        <div>
            <#if error!false >
                <p style="color:red">Incorrect username or password</p>
            </#if>
        </div>
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row">
            <label class="flex flex-v-center">
                <p class="label-input-text mb-1">E-mail:</p>
                <input type="text" name="username" placeholder="your email"  value="${(form.email)!''}" >
                <#if errors?? >
                    <#list errors as fieldError>
                        <#if fieldError.field == 'email'>
                            <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                        </#if>
                    </#list>
                </#if>
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <p class="label-input-text mb-1">Password:</p>
                <input type="password" name="password" placeholder="your password"  value="">
                <#if errors?? >
                    <#list errors as fieldError>
                        <#if fieldError.field == 'password'>
                            <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                        </#if>
                    </#list>
                </#if>
            </label>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Login</button>
        </div>
    </form>
    <br>
    <p>Demo account 1 - user:anna@gmail.com / password:123</p>
    <p>Demo account 2 - user:oleg@gmail.com / password:123</p>
    <p>Demo account 3 - user:mike@gmail.com / password:123</p>

</@main.renderWith>