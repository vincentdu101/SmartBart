import * as React from "react";
import { ITooltipState, ITooltipProps } from "../../types/TooltipTypes";
// import { IStationInfo } from "../../types/StationTypes";
import "./CircleTooltip.css";

export class CircleTooltip extends React.Component<ITooltipProps, ITooltipState> {

    constructor(props: any) {
        super(props);

        this.determinePosition = this.determinePosition.bind(this);
        this.outputTooltipClass = this.outputTooltipClass.bind(this);

        this.state = {
            text: <div>Test</div>,
            station: undefined,
            tooltipActive: false,
            mapHeight: 0
        };
    } 

    public componentDidMount(): void {
        this.setState({});
    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        this.setState({
            text: nextProps.text(nextProps.station),
            station: nextProps.station,
            tooltipActive: nextProps.tooltipActive,
            mapHeight: nextProps.mapHeight
        });
    }

    private outputTooltipClass(): string {
        return this.state.tooltipActive ? "circle-tooltip active" : "circle-tooltip";
    }

    private determinePosition(): {top: string, left: string} {
        let leftBuffer = 20;
        let topBuffer = this.state.mapHeight - 50;
        return {
            left: (leftBuffer) + "px",
            top: (topBuffer) + "px"
        }
    }

    public render(): JSX.Element {
        return (
            <div className={this.outputTooltipClass()} style={this.determinePosition()}>
                {this.state.text}
            </div>
        );
    }

}