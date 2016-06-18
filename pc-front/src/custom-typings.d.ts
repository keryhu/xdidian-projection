/// <reference path="../node_modules/rxjs/add/observable/fromPromise.d.ts" />
/// <reference path="../node_modules/rxjs/add/observable/of.d.ts" />
/// <reference path="../node_modules/rxjs/add/observable/empty.d.ts" />
/// <reference path="../node_modules/rxjs/add/operator/finally.d.ts" />
/// <reference path="../node_modules/rxjs/add/operator/mergeMap.d.ts" />
/// <reference path="../node_modules/rxjs/add/operator/do.d.ts" />
/// <reference path="../node_modules/rxjs/add/operator/map.d.ts" />
/// <reference path="../node_modules/rxjs/add/operator/toPromise.d.ts" />

declare var ENV:string;
declare var HMR:boolean;
interface GlobalEnvironment {
  ENV;
  HMR;
}

interface WebpackModule {
  hot:{
    data?:any,
    idle:any,
    accept(dependencies?:string | string[], callback?:(updatedDependencies?:any) => void):void;
    decline(dependencies?:string | string[]):void;
    dispose(callback?:(data?:any) => void):void;
    addDisposeHandler(callback?:(data?:any) => void):void;
    removeDisposeHandler(callback?:(data?:any) => void):void;
    check(autoApply?:any, callback?:(err?:Error, outdatedModules?:any[]) => void):void;
    apply(options?:any, callback?:(err?:Error, outdatedModules?:any[]) => void):void;
    status(callback?:(status?:string) => void):void | string;
    removeStatusHandler(callback?:(status?:string) => void):void;
  };
}

interface WebpackRequire {
  context(file:string, flag?:boolean, exp?:RegExp):any;
}


interface ErrorStackTraceLimit {
  stackTraceLimit:number;
}


// Extend typings
interface NodeRequire extends WebpackRequire {
}
interface ErrorConstructor extends ErrorStackTraceLimit {
}
interface NodeModule extends WebpackModule {
}
interface Global extends GlobalEnvironment {
}


declare namespace Reflect {
  function decorate(decorators:ClassDecorator[], target:Function):Function;
  function decorate(decorators:(PropertyDecorator | MethodDecorator)[],
                    target:Object,
                    targetKey:string | symbol,
                    descriptor?:PropertyDescriptor):PropertyDescriptor;

  function metadata(metadataKey:any, metadataValue:any):{
    (target:Function):void;
    (target:Object, propertyKey:string | symbol):void;
  };

  function defineMetadata(metadataKey:any, metadataValue:any, target:Object):void;
  function defineMetadata(metadataKey:any,
                          metadataValue:any,
                          target:Object,
                          targetKey:string | symbol):void;

  function hasMetadata(metadataKey:any, target:Object):boolean;
  function hasMetadata(metadataKey:any, target:Object, targetKey:string | symbol):boolean;

  function hasOwnMetadata(metadataKey:any, target:Object):boolean;
  function hasOwnMetadata(metadataKey:any, target:Object, targetKey:string | symbol):boolean;

  function getMetadata(metadataKey:any, target:Object):any;
  function getMetadata(metadataKey:any, target:Object, targetKey:string | symbol):any;

  function getOwnMetadata(metadataKey:any, target:Object):any;
  function getOwnMetadata(metadataKey:any, target:Object, targetKey:string | symbol):any;

  function getMetadataKeys(target:Object):any[];
  function getMetadataKeys(target:Object, targetKey:string | symbol):any[];

  function getOwnMetadataKeys(target:Object):any[];
  function getOwnMetadataKeys(target:Object, targetKey:string | symbol):any[];

  function deleteMetadata(metadataKey:any, target:Object):boolean;
  function deleteMetadata(metadataKey:any, target:Object, targetKey:string | symbol):boolean;
}


// We need this here since there is a problem with Zone.js typings
interface Thenable<T> {
  then<U>(onFulfilled?:(value:T) => U | Thenable<U>,
          onRejected?:(error:any) => U | Thenable<U>):Thenable<U>;
  then<U>(onFulfilled?:(value:T) => U | Thenable<U>,
          onRejected?:(error:any) => void):Thenable<U>;
  catch<U>(onRejected?:(error:any) => U | Thenable<U>):Thenable<U>;
}
