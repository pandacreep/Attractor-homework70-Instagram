<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Please register</h1>
    <form action="/register" method="post" enctype="multipart/form-data">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">E-mail:</span>
                <input type="text" name="email" placeholder="your email"  value="${(form.email)!''}" >
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
                <span class="label-input-text mb-1">Name:</span>
                <input type="text" name="name" placeholder="your name"  value="${(form.name)!''}">
                <#if errors?? >
                    <#list errors as fieldError>
                        <#if fieldError.field == 'name'>
                            <p class="text-danger my-0">${fieldError.defaultMessage!'-- no message --'}</p>
                        </#if>
                    </#list>
                </#if>
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Password:</span>
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
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Avatar:</span>
                <input type="file" name="avatar" accept="image/*">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Description:</span>
                <input type="text" name="description">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Phone number:</span>
                <input type="text" name="phoneNumber">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <span class="label-input-text mb-1">Gender:</span>
                <input type="text" name="gender">
            </label>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Register</button>
        </div>
    </form>

</@main.renderWith>