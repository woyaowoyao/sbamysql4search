import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ZuulgatewaySharedModule } from 'app/shared/shared.module';
import { ZuulgatewayCoreModule } from 'app/core/core.module';
import { ZuulgatewayAppRoutingModule } from './app-routing.module';
import { ZuulgatewayHomeModule } from './home/home.module';
import { ZuulgatewayEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    ZuulgatewaySharedModule,
    ZuulgatewayCoreModule,
    ZuulgatewayHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ZuulgatewayEntityModule,
    ZuulgatewayAppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class ZuulgatewayAppModule {}
