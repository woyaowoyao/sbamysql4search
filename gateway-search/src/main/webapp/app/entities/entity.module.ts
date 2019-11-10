import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'payment-record',
        loadChildren: () => import('./payment-record/payment-record.module').then(m => m.GatewaysearchPaymentRecordModule)
      },
      {
        path: 'mentor',
        loadChildren: () => import('./mentor/mentor.module').then(m => m.GatewaysearchMentorModule)
      },
      {
        path: 'mentor-skill',
        loadChildren: () => import('./mentor-skill/mentor-skill.module').then(m => m.GatewaysearchMentorSkillModule)
      },
      {
        path: 'my-calendar',
        loadChildren: () => import('./my-calendar/my-calendar.module').then(m => m.GatewaysearchMyCalendarModule)
      },
      {
        path: 'training',
        loadChildren: () => import('./training/training.module').then(m => m.GatewaysearchTrainingModule)
      },
      {
        path: 'training-record',
        loadChildren: () => import('./training-record/training-record.module').then(m => m.GatewaysearchTrainingRecordModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class GatewaysearchEntityModule {}
