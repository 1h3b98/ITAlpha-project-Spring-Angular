import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListfeedsComponent } from './listfeeds.component';

describe('ListfeedsComponent', () => {
  let component: ListfeedsComponent;
  let fixture: ComponentFixture<ListfeedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListfeedsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListfeedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
