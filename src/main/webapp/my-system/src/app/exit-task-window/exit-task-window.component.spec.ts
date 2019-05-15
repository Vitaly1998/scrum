import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExitTaskWindowComponent } from './exit-task-window.component';

describe('ExitTaskWindowComponent', () => {
  let component: ExitTaskWindowComponent;
  let fixture: ComponentFixture<ExitTaskWindowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExitTaskWindowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExitTaskWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
