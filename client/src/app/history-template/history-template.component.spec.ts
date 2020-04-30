import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryTemplateComponent } from './history-template.component';

describe('HistoryTemplateComponent', () => {
  let component: HistoryTemplateComponent;
  let fixture: ComponentFixture<HistoryTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
