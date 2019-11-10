import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { GatewaysearchSharedModule } from 'app/shared/shared.module';
import { GatewaysearchCoreModule } from 'app/core/core.module';
import { GatewaysearchAppRoutingModule } from './app-routing.module';
import { GatewaysearchHomeModule } from './home/home.module';
import { GatewaysearchEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    GatewaysearchSharedModule,
    GatewaysearchCoreModule,
    GatewaysearchHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GatewaysearchEntityModule,
    GatewaysearchAppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class GatewaysearchAppModule {}
