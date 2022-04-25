<#import "partials/main.ftlh" as main>

<@main.renderWith scripts=[]>

    <h1 class="my-3">Add new post</h1>
    <form action="/posts/add" method="post" enctype="multipart/form-data">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="row">
            <label class="flex flex-v-center">
                <p class="label-input-text mb-1">Image:</p>
                <input type="file" name="file" accept="image/*">
            </label>
        </div>
        <div class="row mt-3">
            <label class="flex flex-v-center">
                <p class="label-input-text mb-1">Description:</p>
                <input type="description" name="description">
            </label>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Add post</button>
        </div>
    </form>

</@main.renderWith>